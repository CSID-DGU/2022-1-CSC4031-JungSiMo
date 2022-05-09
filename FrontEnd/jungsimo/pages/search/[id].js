import Layout from "../layout";
import { category2 } from "../../public/sampleData";
import Link from "next/link";

const SearchShow = () => {
	return (
		<Layout>
			<div className="flex flex-col mt-[60px]">
				<span className="mx-[15px] text-xs">검색 조건 : 휴대폰/ 삼성전자</span>
				<div className="flex h-[45px] mt-[5px]">
					<input
						type="text"
						className="mx-[15px] border border-[#D1D5DB] w-full p-2"
						placeholder="원하는 스펙이 없을 경우 검색하세요"
					/>
					<button
						type="submit"
						className="flex items-center justify-center text-white bg-blue-800 mr-[15px] shrink-0 rounded-[5px] w-[79px]"
					>
						검색
					</button>
				</div>
				<div className="mt-[10px] w-full">
					<table class="table-auto w-full">
						<thead className="h-[45px]">
							<tr className="text-white bg-blue-800">
								<td className="text-left pl-[15px]">이름</td>
								<td className="text-left">HDD</td>
								<td className="text-left">RAM</td>
							</tr>
						</thead>
						<tbody>
							{category2?.map((item) => {
								return (
									<Link href="/result/1">
										<tr
											className="border-b border-[#AAAAAA] h-[45px] cursor-pointer"
											key={item?.id}
										>
											<td className="pl-[15px] w-[50%] overflow-auto">
												{item?.name}
											</td>
											<td className="w-[25%] overflow-x-auto">
												{item?.detail?.hdd}
											</td>
											<td className="w-[25%] overflow-x-auto">
												{item?.detail?.ram}
											</td>
										</tr>
									</Link>
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
