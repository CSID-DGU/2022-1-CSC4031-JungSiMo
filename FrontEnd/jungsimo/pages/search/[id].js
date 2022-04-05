import Layout from "../layout";

const SearchShow = () => {
	return (
		<Layout>
			<div className="flex flex-col mt-[60px]">
				<span className="mx-[15px] text-xs">검색 조건 : 품목/ 상품명</span>
				<div className="flex h-[45px] mt-[5px]">
					<input
						type="text"
						className="mx-[15px] border border-[#D1D5DB] w-full p-2"
						placeholder="원하는 스펙이 없을 경우 검색하세요"
					/>
					<button
						type="submit"
						className="flex items-center justify-center text-white bg-blue-500 mr-[15px] shrink-0 rounded-[5px] w-[79px]"
					>
						검색
					</button>
				</div>
				<div className="mt-[10px] w-full">
					<table class="table-auto w-full">
						<thead className="h-[45px]">
							<tr className="text-white bg-blue-500">
								<td className="text-left pl-[15px]">이름</td>
								<td className="text-left">상세스펙</td>
							</tr>
						</thead>
						<tbody>
							<tr className="border-b border-[#AAAAAA] h-[45px]">
								<td className="pl-[15px] w-[25%] overflow-auto">The Sliding</td>
								<td className="w-[75%] overflow-x-auto">
									Malcolm Lockyer Lorem ipsum dolor sit amet consectetur
									adipisicing elit. Ipsam, esse veniam? Veniam exercitationem
									aspernatur adipisci quaerat, quia velit, nostrum officia
									temporibus tempore et dicta, nesciunt quos atque sunt
									inventore reiciendis.
								</td>
							</tr>
              <tr className="border-b border-[#AAAAAA] h-[45px]">
								<td className="pl-[15px] w-[25%] overflow-auto">The Sliding</td>
								<td className="w-[75%] overflow-x-auto">
									Malcolm Lockyer Lorem ipsum dolor sit amet consectetur
									adipisicing elit. Ipsam, esse veniam? Veniam exercitationem
									aspernatur adipisci quaerat, quia velit, nostrum officia
									temporibus tempore et dicta, nesciunt quos atque sunt
									inventore reiciendis.
								</td>
							</tr>
              <tr className="border-b border-[#AAAAAA] h-[45px]">
								<td className="pl-[15px] w-[25%] overflow-auto">The Sliding</td>
								<td className="w-[75%] overflow-x-auto">
									Malcolm Lockyer Lorem ipsum dolor sit amet consectetur
									adipisicing elit. Ipsam, esse veniam? Veniam exercitationem
									aspernatur adipisci quaerat, quia velit, nostrum officia
									temporibus tempore et dicta, nesciunt quos atque sunt
									inventore reiciendis.
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</Layout>
	);
};

export default SearchShow;
