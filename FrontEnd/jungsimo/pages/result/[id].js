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

const ResultShow = () => {
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
				text: "Chart.js Line Chart - Multi Axis",
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

	const labels = chartLabel;

	const data = {
		labels,
		datasets: [
			{
				label: "최고가",
				data: chartBig?.chartY,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#323ea8",
				yAxisID: "y",
			},
			{
				label: "평균가",
				data: chartAvg?.chartY,
				borderColor: "rgb(255, 99, 132)",
				backgroundColor: "#32a84a",
				yAxisID: "y",
			},
			{
				label: "최저가",
				data: chartSmall?.chartY,
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
					<span className="text-xl font-bold">{product?.name}</span>
					<Image src={sample1} width={128} height={128} />
				</div>
				<div className="flex flex-col justify-center ml-4 text-sm">
					{product?.detail?.hdd !== "" && (
						<div className="flex">
							<span>HDD:&nbsp;</span>
							<span>{product?.detail?.hdd}</span>
						</div>
					)}
					{product?.detail?.ram !== "" && (
						<div className="flex">
							<span>RAM:&nbsp;</span>
							<span>{product?.detail?.ram}</span>
						</div>
					)}
				</div>
			</div>
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
