import Layout from "./layout";
import arrowB from "../assets/icons/arrow_bottom.svg";
import Image from "next/image";
import { useState } from "react";
import { mostKeywords, category1 } from "../public/sampleData";
import { keys } from "lodash";
import { useEffect } from "react";
import Link from "next/link";
import giveCoffee from "../assets/images/give_coffee.svg";
import axios from "axios";

const Home = (props) => {
	const [isSelectOpen1, setIsSelectOpen1] = useState(false);
	const [isSelectOpen2, setIsSelectOpen2] = useState(false);
	const [name1, setName1] = useState("제품을 선택하세요");
	const [name2, setName2] = useState("브랜드를 선택하세요");

	const { category } = props;

	const clickSelect1 = (e) => {
		console.log(e.currentTarget.textContent);
		const selectCategoryName = {
			categoryName: "이어폰",
		};
		axios
			.post("http://localhost:8080/api/v1/search/brand", selectCategoryName)
			.then((response) => {
				console.log(`asdfsad : ${response.data}`);
			});
	};

	const clickSelect2 = (e) => {
		setName2(e.currentTarget.textContent);
	};

	useEffect(() => {
		setName2("브랜드를 선택하세요");
	}, [name1]);

	return (
		<Layout>
			{/* 지금뜨는키워드 */}
			<div className="flex flex-col px-[15px] mt-[60px]">
				<span className="text-sm mt-[20px]">#지금_뜨는_키워드</span>
				<div className="grid grid-cols-4 gap-[7px] mt-4">
					{mostKeywords?.map((keyword, index) => {
						return (
							<span
								className={`text-white  ${
									index % 2 === 0 ? "bg-blue-500" : "bg-blue-400"
								} text-sm py-2 flex items-center justify-center rounded-[10px] shadow-[0px_4px_4px_rgba(0,0,0,0.25)]`}
							>
								{keyword}
							</span>
						);
					})}
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
						<div className="z-[100] absolute flex flex-col  overflow-scroll mx-auto top-[43px] border border-gray-400 w-[200px] rounded-br-lg rounded-bl-lg bg-white">
							{category?.map((cate) => {
								return (
									<div
										onClick={(e) => clickSelect1(e)}
										key={cate?.categoryId}
										className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
									>
										<span className="mx-auto">{cate?.categoryName}</span>
									</div>
								);
							})}
						</div>
					)}
				</button>
			</div>

			{/* select btn2 */}
			<div className="relative w-full mt-4">
				<button
					disabled={name1 === "제품을 선택하세요"}
					onClick={() => {
						setIsSelectOpen2(!isSelectOpen2);
					}}
					className={`w-[200px] h-[45px] flex items-center border justify-center border-gray-500 mx-auto relative ${
						name1 === "제품을 선택하세요" ? "opacity-30" : ""
					} ${isSelectOpen2 ? "rounded-tr-lg rounded-tl-lg" : "rounded-lg"}`}
				>
					<span className="mr-4">{name2}</span>
					<Image src={arrowB} alt="셀렉트열기" className="w-[10px] h-[6px]" />
					{isSelectOpen2 && (
						<div className="z-[100] absolute flex flex-col overflow-scroll mx-auto top-[43px] border border-gray-400 w-[200px] rounded-br-lg rounded-bl-lg bg-white">
							{category1?.[name1]?.map((name) => {
								return (
									<div
										onClick={(e) => clickSelect2(e)}
										className="flex items-center justsify-center h-[45px] mx-auto border-b w-full shrink-0"
									>
										<span className="mx-auto">{name}</span>
									</div>
								);
							})}
						</div>
					)}
				</button>
			</div>

			{/* submit btn */}
			{/* 임시 링크 */}
			<Link href="/search/1">
				<button className="rounded-lg bg-blue-500 text-white flex items-center justify-center w-[200px] h-[45px] mt-4 mx-auto">
					탐색
				</button>
			</Link>

			<div className="flex flex-col justify-center w-full mt-[20vh] items-center">
				<span className="mb-4 text-sm">써써유의 서비스가 마음에 드나요?</span>
				<Image src={giveCoffee} />
			</div>
		</Layout>
	);
};

export default Home;

export async function getServerSideProps() {
	const fetchingCategory = await fetch(
		"http://localhost:8080/api/v1/search/category"
	);
	const category = await fetchingCategory.json();

	return { props: { category: category } };
}
