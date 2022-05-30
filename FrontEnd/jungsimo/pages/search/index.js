import Layout from "../layout";

import Link from "next/link";
import { useEffect, useState, useRef } from "react";
import { useRouter } from "next/router";
import axios from "axios";
import { collapseTextChangeRangesAcrossMultipleVersions } from "typescript";

const SearchShow = () => {
	const [items, setItems] = useState([]);

	const router = useRouter();
	const searchRef = useRef();
	const [bodyParams, setBodyParams] = useState({
		categoryName: null,
		itemBrand: null,
	});

	useEffect(() => {
		axios
			.post("http://localhost:8080/api/v1/search/item", bodyParams)
			.then((response) => {
				setItems(response?.data);
				console.log(response?.data);
			});
	}, [router.isReady, router.query, bodyParams]);

	useEffect(() => {
		setBodyParams({
			categoryName: router?.query?.categoryName,
			itemBrand: router?.query?.itemBrand,
		});
	}, []);

	const searchItems = () => {
		axios
			.post("http://localhost:8080/api/v1/search/item/keyword", {
				...bodyParams,
				keyword: searchRef.current.value,
			})
			.then((response) => {
				console.log("성공");
			})
			.catch((error) => {
				console.log("실패");
			});
	};

	const goResultLink = (categoryId, itemId) => {
		axios
			.post("http://localhost:8080/api/v1/detail/info", {
				categoryId: categoryId,
				itemId: itemId,
			})
			.then((response) => {
				window.location = `/result?itemId=${itemId}&categoryId=${categoryId}`;
			});
	};

	return (
		<Layout>
			<div className="flex flex-col mt-[60px]">
				<span className="mx-[15px] text-xs">
					검색 조건 : {router?.query?.itemBrand} / {router?.query?.categoryName}
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
						onClick={() => {
							searchItems;
						}}
					>
						검색
					</button>
				</div>
				<div className="mt-[10px] w-full">
					<table className="w-full table-auto">
						<thead className="h-[45px]">
							<tr className="text-white bg-blue-800">
								<td className="text-left pl-[15px]">이름</td>
								<td className="text-left">HDD</td>
								<td className="text-left">RAM</td>
							</tr>
						</thead>
						<tbody>
							{items?.map((item) => {
								return (
									<tr
										onClick={goResultLink(item?.categoryId, item?.itemId)}
										key={item?.itemId}
										className="w-full border-b border-[#AAAAAA]  cursor-pointer h-[45px]"
									>
										<td className="pl-[15px] overflow-auto">
											{item?.itemName}
										</td>
										<td className="overflow-x-auto">{item?.detail?.hdd}</td>
										<td className="overflow-x-auto">{item?.detail?.ram}</td>
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
