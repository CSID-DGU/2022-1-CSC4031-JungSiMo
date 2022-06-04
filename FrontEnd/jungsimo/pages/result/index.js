import Layout from "../layout";
import Image from "next/image";
import "swiper/css";
import React from "react";
import {
	Chart as ChartJS,
	CategoryScale,
	LinearScale,
	PointElement,
	LineElement,
	Title,
	Tooltip,
	Legend,
} from "chart.js";
import { Line } from "react-chartjs-2";
import Link from "next/link";
import { useEffect, useState } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";
import { values, omit, reverse, filter, isNull } from "lodash";
import noImage from "../../assets/images/noImage.png";

const ResultShow = () => {
	const [cookies, setCookies] = useCookies(["jungsimo"]);

	const [priceParams, setPriceParams] = useState({
		itemId: cookies["itemId"],
		itemPricePeriod: "1d",
	});

	const [priceDetail, setPriceDetail] = useState({});

	const [priceBig, setPriceBig] = useState([]);
	const [priceAvg, setPriceAvg] = useState([]);
	const [priceSmall, setPriceSmall] = useState([]);

	const [priceUrls, setPriceUrls] = useState({});

	const [labels, setLabels] = useState([]);

	const [screen, setScreen] = useState({
		itemId: null,
		categoryId: null,
		itemName: null,
	});

	useEffect(() => {
		setScreen({
			itemId: cookies["itemId"],
			categoryId: cookies["categoryId"],
			itemName: cookies["itemName"],
		});
	}, []);

	useEffect(() => {
		axios
			.post("http://localhost:8080/api/v1/detail/info", {
				itemId: screen?.itemId,
				categoryId: screen?.categoryId,
			})
			.then((response) => {
				setPriceDetail(response?.data);
			});
	}, [screen]);

	useEffect(() => {
		setLabels([]);
		setPriceBig([]);
		setPriceAvg([]);
		setPriceSmall([]);
		axios
			.post("http://localhost:8080/api/v1/detail/price/change", priceParams)
			.then((response) => {
				reverse(response?.data).map((res) => {
					if (res?.length !== 0) {
						setLabels((labels) => [...labels, res[0]?.itemDate]);

						let filterdBig = filter(res, (r) => {
							return r.itemState === "상";
						});

						if (filterdBig?.length === 1) {
							setPriceBig((priceBig) => [...priceBig, filterdBig[0].itemPrice]);
						} else {
							setPriceBig((priceAvg) => [...priceAvg, 0]);
						}

						let filterdAvg = filter(res, (r) => {
							return r.itemState === "중";
						});

						if (filterdAvg?.length === 1) {
							setPriceAvg((priceAvg) => [...priceAvg, filterdAvg[0].itemPrice]);
						} else {
							setPriceAvg((priceAvg) => [...priceAvg, 0]);
						}

						let filterdSmall = filter(res, (r) => {
							return r.itemState === "하";
						});

						if (filterdSmall?.length === 1) {
							setPriceSmall((priceSmall) => [
								...priceSmall,
								filterdSmall[0].itemPrice,
							]);
						} else {
							setPriceSmall((priceSmall) => [...priceSmall, 0]);
						}
					} else {
						setLabels((labels) => [...labels, null]);
						setPriceBig((priceBig) => [...priceBig, null]);
						setPriceAvg((priceAvg) => [...priceAvg, null]);
						setPriceSmall((priceSmall) => [...priceSmall, null]);
					}
				});
			});

		axios
			.post("http://localhost:8080/api/v1/detail/price/summary", {
				itemId: priceParams?.itemId,
				itemPricePeriod: priceParams?.itemPricePeriod,
			})
			.then((response) => {
				setPriceUrls(response?.data);
			});
	}, [priceParams]);

	ChartJS.register(
		CategoryScale,
		LinearScale,
		PointElement,
		LineElement,
		Title,
		Tooltip,
		Legend
	);

	const options = {
		responsive: true,
		interaction: {
			mode: "index",
			intersect: false,
		},
		stacked: false,
		plugins: {
			title: {
				display: true,
				text: "기간 별 중고 거래",
			},
		},
		scales: {
			y: {
				type: "linear",
				display: true,
				position: "left",
			},
			y1: {
				type: "linear",
				display: true,
				position: "right",
				grid: {
					drawOnChartArea: false,
				},
			},
		},
	};

	const data = {
		labels,
		datasets: [
			{
				label: "상태 상",
				data: priceBig,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#323ea8",
				yAxisID: "y",
			},
			{
				label: "상태 중",
				data: priceAvg,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#32a84a",
				yAxisID: "y",
			},
			{
				label: "상태 하",
				data: priceSmall,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#ebde31",
				yAxisID: "y",
			},
		],
	};

	return (
		<Layout>
			<div className="flex mt-[30px] px-[15px]">
				<div className="flex flex-col shrink-0">
					<span className="text-xl font-bold">{screen?.itemName}</span>
					<Image
						src={priceDetail?.itemImage || noImage}
						width={128}
						height={128}
					/>
				</div>
				<div className="grid justify-center grid-cols-3 gap-2 ml-4 text-sm">
					{values(omit(priceDetail, ["itemId", "categoryId", "itemImage"])).map(
						(value, index) => {
							return <span key={`option_${index}`} className="flex items-center justify-center text-center break-all bg-slate-100">{value}</span>;
						}
					)}
				</div>
			</div>
			<div className="w-full mt-4 text-center">
				<select
					className="mx-auto"
					onChange={(e) => {
						setPriceParams({
							...priceParams,
							itemPricePeriod: e.currentTarget.value,
						});
					}}
				>
					<option value="1d">일</option>
					<option value="1w">주</option>
					<option value="1m">월</option>
				</select>
			</div>
			<Line options={options} data={data} />
			{priceUrls?.maxPriceItem?.length !== 0 &&
				!isNull(priceUrls?.maxPriceItem) && (
					<div className="flex flex-col px-[15px] mt-8">
						<span className="font-bold text">최고 가격</span>

						<div className="flex mt-2 text-sm">
							<span className="w-[20%] shrink-0 font-bold">가격</span>
							<span className="w-[20%] shrink-0 font-bold">상태</span>
							<span className="w-[20%] shirnk-0 font-bold">사이트</span>
							<span className="font-bold truncate">제목</span>
						</div>

						{priceUrls?.maxPriceItem?.map((item, index) => {
							return (
								<Link href={item?.itemUrl} key={`maxPrice${index}`}>
									<div className="flex mt-2 text-sm cursor-pointer bg-slate-100">
										<span className="w-[20%] shrink-0">{item?.itemPrice}</span>
										<span className="w-[20%] shrink-0">{item?.itemState}</span>
										<span className="w-[20%] shirnk-0">{item?.itemSource}</span>
										<span className="truncate">제목이 아직 없어요</span>
									</div>
								</Link>
							);
						})}
					</div>
				)}

			{priceUrls?.avgPriceItem?.length !== 0 &&
				!isNull(priceUrls?.avgPriceItem) && (
					<div className="flex flex-col px-[15px] mt-8">
						<span className="font-bold">평균 가격</span>
						<div className="flex mt-2 text-sm">
							<span className="w-[20%] shrink-0 font-bold">가격</span>
							<span className="w-[20%] shrink-0 font-bold">상태</span>
							<span className="w-[20%] shirnk-0 font-bold">사이트</span>
							<span className="font-bold truncate">제목</span>
						</div>

						{priceUrls?.avgPriceItem?.map((item, index) => {
							return (
								<Link href={item?.itemUrl} key={`avgPrice${index}`}>
									<div className="flex mt-2 text-sm cursor-pointer bg-slate-100">
										<span className="w-[20%] shrink-0">{item?.itemPrice}</span>
										<span className="w-[20%] shrink-0">{item?.itemState}</span>
										<span className="w-[20%] shirnk-0">{item?.itemSource}</span>
										<span className="truncate">제목이 아직 없어요</span>
									</div>
								</Link>
							);
						})}
					</div>
				)}

			{priceUrls?.minPriceItem?.length !== 0 &&
				!isNull(priceUrls?.minPriceItem) && (
					<div className="flex flex-col px-[15px] mt-8">
						<span className="font-bold">최저 가격</span>
						<div className="flex mt-2 text-sm">
							<span className="w-[20%] shrink-0 font-bold">가격</span>
							<span className="w-[20%] shrink-0 font-bold">상태</span>
							<span className="w-[20%] shirnk-0 font-bold">사이트</span>
							<span className="font-bold truncate">제목</span>
						</div>

						{priceUrls?.minPriceItem?.map((item, index) => {
							return (
								<Link href={item?.itemUrl} key={`minPrice${index}`}>
									<div className="flex mt-2 text-sm cursor-pointer bg-slate-100">
										<span className="w-[20%] shrink-0">{item?.itemPrice}</span>
										<span className="w-[20%] shrink-0">{item?.itemState}</span>
										<span className="w-[20%] shirnk-0">{item?.itemSource}</span>
										<span className="truncate">제목이 아직 없어요</span>
									</div>
								</Link>
							);
						})}
					</div>
				)}
		</Layout>
	);
};

export default ResultShow;
