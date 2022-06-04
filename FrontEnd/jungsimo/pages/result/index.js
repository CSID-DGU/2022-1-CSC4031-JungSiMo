import Layout from "../layout";
import sample1 from "../../assets/images/phone_sample.png";
import Image from "next/image";
import "swiper/css";
import {
	product,
	chartBig,
	chartSmall,
	chartAvg,
	labels as chartLabel,
} from "../../public/sampleData";
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
import { page3Sample } from "../../public/sampleData";
import { values, omit, reverse, filter } from "lodash";

const ResultShow = () => {
	const [cookies, setCookies] = useCookies(["jungsimo"]);

	const [priceParams, setPriceParams] = useState({
		itemId: cookies["itemId"],
		itemPricePeriod: "1d",
	});

	const [priceBig, setPriceBig] = useState([]);
	const [priceAvg, setPriceAvg] = useState([]);
	const [priceSmall, setPriceSmall] = useState([]);

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
		console.log(cookies["itemId"]);
		console.log(cookies["categoryId"]);
		axios
			.post("https://localhost:8080/api/v1/detail/info", {
				// itemId: cookies["itemId"],
				// categoryId: cookies["categoryId"],
				itemId: "6",
				categoryId: "4",
			})
			.then((response) => {
				console.log(response);
			});
	}, []);

	useEffect(() => {
		setLabels([]);
		setPriceBig([]);
		setPriceAvg([]);
		setPriceSmall([]);
		axios
			.post("http://localhost:8080/api/v1/detail/price/change", priceParams)
			.then((response) => {
				console.log(response?.data);
				reverse(response?.data).map((res) => {
					if (res?.length !== 0) {
						setLabels((labels) => [...labels, res[0]?.itemDate]);

						let filterdBig = filter(res, (r) => {
							return r.itemState === "상";
						});

						if (filterdBig?.length === 1) {
							setPriceBig((priceBig) => [...priceBig, filterdBig[0].itemPrice]);
						} else {
							setPriceBig((priceAvg) => [...priceAvg, null]);
						}

						let filterdAvg = filter(res, (r) => {
							return r.itemState === "중";
						});

						if (filterdAvg?.length === 1) {
							setPriceAvg((priceAvg) => [...priceAvg, filterdAvg[0].itemPrice]);
						} else {
							setPriceAvg((priceAvg) => [...priceAvg, null]);
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
							setPriceSmall((priceSmall) => [...priceSmall, null]);
						}
					}
				});
			});
	}, [priceParams]);

	console.log(priceBig);
	console.log(priceAvg);
	console.log(priceSmall);
	console.log(labels);

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
				label: "최고가",
				data: priceBig,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#323ea8",
				yAxisID: "y",
			},
			{
				label: "평균가",
				data: priceAvg,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#32a84a",
				yAxisID: "y",
			},
			{
				label: "최저가",
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
					<Image src={`${page3Sample.itemImage}`} width={128} height={128} />
				</div>
				<div className="grid justify-center grid-cols-4 ml-4 text-sm">
					{values(omit(page3Sample, ["itemId", "categoryId", "itemImage"])).map(
						(value, index) => {
							return <span key={`option_${index}`}>{value}</span>;
						}
					)}
				</div>
			</div>
			<select
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
			<Line options={options} data={data} />
			<div className="flex flex-col px-[15px] mt-8">
				<span className="text-sm font-bold">최고가격</span>
				<Link href="https://www.daangn.com/articles/387422498">
					<div className="flex mt-2 text-sm">
						<span className="w-[20%] shrink-0">50,000</span>
						<span className="w-[20%] shirnk-0">당근마켓</span>
						<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
					</div>
				</Link>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
			</div>

			<div className="flex flex-col px-[15px] mt-8">
				<span className="text-sm font-bold">평균가격</span>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
			</div>

			<div className="flex flex-col px-[15px] mt-8">
				<span className="text-sm font-bold">최저가격</span>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
				<div className="flex mt-2 text-sm">
					<span className="w-[20%] shrink-0">50,000</span>
					<span className="w-[20%] shirnk-0">당근마켓</span>
					<span className="truncate">LG그램 / LG GRAM / 오만원에 adfsda</span>
				</div>
			</div>
		</Layout>
	);
};

export default ResultShow;
