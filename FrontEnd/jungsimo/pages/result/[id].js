import Layout from "../layout";
import sample1 from "../../assets/images/sample1.png";
import Image from "next/image";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import { Line } from "react-chartjs-2";
import Chart from "chart.js/auto";

const ResultShow = () => {
	const data = {
		labels: ["January", "February", "March", "April", "May", "June", "July"],
		datasets: [
			{
				label: "My First dataset",
				fill: false,
				lineTension: 0.1,
				backgroundColor: "rgba(75,192,192,0.4)",
				borderColor: "rgba(75,192,192,1)",
				borderCapStyle: "butt",
				borderDash: [],
				borderDashOffset: 0.0,
				borderJoinStyle: "miter",
				pointBorderColor: "rgba(75,192,192,1)",
				pointBackgroundColor: "#fff",
				pointBorderWidth: 1,
				pointHoverRadius: 5,
				pointHoverBackgroundColor: "rgba(75,192,192,1)",
				pointHoverBorderColor: "rgba(220,220,220,1)",
				pointHoverBorderWidth: 2,
				pointRadius: 1,
				pointHitRadius: 10,
				data: [65, 59, 80, 81, 56, 55, 40],
			},
		],
	};

	return (
		<Layout>
			<div className="mx-[15px] flex mt-[30px]">
				<div className="flex flex-col shrink-0">
					<span className="text-xl font-bold">LG그램</span>
					<Image src={sample1} className="w-[128px] h-[128px]" />
				</div>
				<Swiper
					spaceBetween={50}
					slidesPerView={1}
					className="border border-[#E7E8E8] w-full"
				>
					<SwiperSlide>Slide1</SwiperSlide>
					<SwiperSlide>Slide2</SwiperSlide>
					<SwiperSlide>Slide3</SwiperSlide>
					<SwiperSlide>Slide4</SwiperSlide>
				</Swiper>
			</div>

			<Line
				className=""
				data={data}
				width={400}
				height={400}
				options={{ layout: { padding: 15 } }}
			/>
		</Layout>
	);
};

export default ResultShow;
