import Layout from "./layout";
import arrowB from "../assets/icons/arrow_bottom.svg";
import Image from "next/image";
import { useState } from "react";

const Home = () => {
	const [isSelectOpen1, setIsSelectOpen1] = useState(false);
	const [isSelectOpen2, setIsSelectOpen2] = useState(false);
	const [name1, setName1] = useState("제품을 선택하세요");
	const [name2, setName2] = useState("브랜드를 선택하세요");

	const clickSelect1 = (e) => {
		setName1(e.currentTarget.textContent);
	};

	const clickSelect2 = (e) => {
		setName2(e.currentTarget.textContent);
	};

	return (
		<Layout>
			{/* 지금뜨는키워드 */}
			<div className="flex flex-col mx-[15px]">
				<span className="text-sm mt-[20px]">#지금_뜨는_키워드</span>
				<div className="grid grid-cols-4 gap-[7px] mt-4">
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
					<span className="text-white bg-blue-500 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]">
						냉장고
					</span>
				</div>
			</div>

			{/* select btn1 */}
			<div className="mt-[70px] w-full relative">
				<button
					onClick={() => {
						setIsSelectOpen1(!isSelectOpen1);
					}}
					className={`w-[200px] h-[45px] flex items-center border justify-center border-gray-500 mx-auto relative ${
						isSelectOpen1 ? "rounded-tr-lg rounded-tl-lg" : "rounded-lg"
					}`}
				>
					<span className="mr-4">{name1}</span>
					<Image src={arrowB} alt="셀렉트열기" className="w-[10px] h-[6px]" />
					{isSelectOpen1 && (
						<div className="z-[100] absolute flex flex-col h-[300px] overflow-scroll mx-auto top-[43px] border border-gray-400 w-[200px] rounded-br-lg rounded-bl-lg bg-white">
							<div
								onClick={(e) => clickSelect1(e)}
								className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
							>
								<span className="mx-auto">스마트폰</span>
							</div>
							<div
								onClick={(e) => clickSelect1(e)}
								className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
							>
								<span className="mx-auto">스마트폰</span>
							</div>
							<div
								onClick={(e) => clickSelect1(e)}
								className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
							>
								<span className="mx-auto">스마트폰</span>
							</div>
							<div
								onClick={(e) => clickSelect1(e)}
								className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
							>
								<span className="mx-auto">스마트폰</span>
							</div>
							<div
								onClick={(e) => clickSelect1(e)}
								className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
							>
								<span className="mx-auto">스마트폰</span>
							</div>
						</div>
					)}
				</button>
			</div>

			{/* select btn2 */}
			<div className="mt-4 w-full relative">
				<button
					onClick={() => {
						setIsSelectOpen2(!isSelectOpen2);
					}}
					className={`w-[200px] h-[45px] flex items-center border justify-center border-gray-500 mx-auto relative ${
						isSelectOpen2 ? "rounded-tr-lg rounded-tl-lg" : "rounded-lg"
					}`}
				>
					<span className="mr-4">{name2}</span>
					<Image src={arrowB} alt="셀렉트열기" className="w-[10px] h-[6px]" />
					{isSelectOpen2 && (
						<div className="z-[100] absolute flex flex-col h-[300px] overflow-scroll mx-auto top-[43px] border border-gray-400 w-[200px] rounded-br-lg rounded-bl-lg">
							<div
								onClick={(e) => clickSelect2(e)}
								className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
							>
								<span className="mx-auto">삼성</span>
							</div>
						</div>
					)}
				</button>
			</div>

			{/* submit btn */}
			<button className="rounded-lg bg-blue-500 text-white flex items-center justify-center w-[200px] h-[45px] mt-4 mx-auto">
				탐색
			</button>
		</Layout>
	);
};

export default Home;
