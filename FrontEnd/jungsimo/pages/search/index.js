import Layout from "../layout";
import { useEffect, useState, useRef } from "react";
import axios from "axios";
import { useCookies } from "react-cookie";

const SearchShow = () => {
	const [cookies, setCookies] = useCookies(["jungsimo"]);

	const [items, setItems] = useState(null);

	const [screen, setScreen] = useState({
		product: null,
		brand: null,
	});

	useEffect(() => {
		setScreen({
			product: cookies["product"],
			brand: cookies["brand"],
		});
	}, []);

	const searchRef = useRef();

	useEffect(() => {
		axios
			.post("http://localhost:8080/api/v1/search/item", {
				categoryName: cookies["product"],
				itemBrand: cookies["brand"],
			})
			.then((response) => {
				setItems(response?.data);
			});
	}, []);

	const searchItems = () => {
		axios
			.post("http://localhost:8080/api/v1/search/item/keyword", {
				categoryName: cookies["product"],
				itemBrand: cookies["brand"],
				keyword: searchRef.current.value,
			})
			.then((response) => {
				setItems(response?.data);
			});
	};

	const clickItemDetail = (itemId, categoryId, itemName) => {
		setCookies("itemId", itemId);
		setCookies("categoryId", categoryId);
		setCookies("itemName", itemName);

		location.href = "/result";
	};

	return (
		<Layout>
			<div className="flex flex-col mt-[60px]">
				<span className="mx-[15px] text-xs">
					검색 조건 : {screen?.product} / {screen?.brand}
				</span>
				<div className="flex h-[45px] mt-[5px]">
					<input
						type="text"
						className="mx-[15px] border border-[#D1D5DB] w-full p-2"
						placeholder="원하는 스펙이 없을 경우 검색하세요"
						ref={searchRef}
					/>
					<button
						type="submit"
						className="flex items-center justify-center text-white bg-blue-800 mr-[15px] shrink-0 rounded-[5px] w-[79px]"
						onClick={searchItems}
					>
						검색
					</button>
				</div>
				<div className="mt-[10px] w-full">
					<table className="w-full table-auto">
						<thead className="h-[45px]">
							<tr className="text-white bg-blue-800">
								<td className="text-left pl-[15px]">이름</td>
							</tr>
						</thead>
						<tbody>
							{items?.map((item) => {
								return (
									<tr
										onClick={() =>
											clickItemDetail(
												item?.itemId,
												item?.categoryId,
												item?.itemName
											)
										}
										key={item?.itemId}
										className="w-full border-b border-[#AAAAAA]  cursor-pointer h-[45px]"
									>
										<td className="pl-[15px] overflow-auto">
											{item?.itemName}
										</td>
									</tr>
								);
							})}
						</tbody>
					</table>
				</div>
			</div>
		</Layout>
	);
};

export default SearchShow;
