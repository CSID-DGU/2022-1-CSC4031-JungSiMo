const mostKeywords = [
	"갤럭시 폴드 2",
	"아이폰 13",
	"갤럭시 워치4",
	"애플 M1",
	"아이패드 Air 5",
	"갤럭시 S22",
	"갤럭시 S22 울트라",
	"갤럭시 S22 플러스",
];

const category1 = {
	휴대폰: ["애플", "삼성전자"],
	태블릿: ["애플", "삼성전자", "마이크로소프트"],
	노트북: ["애플", "삼성전자", "LG"],
	웨어러블: ["애플", "삼성전자", "샤오미"],
};

const category2 = [
	{ id: 1, name: "갤럭시 S22 플러스", detail: { hdd: "256GB" } },
	{ id: 2, name: "갤럭시 S22 울트라", detail: { hdd: "1TB", ram: "" } },
	{ id: 3, name: "갤럭시 S22 울트라", detail: { hdd: "512GB", ram: "" } },
	{ id: 4, name: "갤럭시 S22 울트라", detail: { hdd: "256GB", ram: "" } },
	{ id: 5, name: "갤럭시 S22", detail: { hdd: "256GB", ram: "" } },
	{ id: 6, name: "갤럭시 S21 플러스", detail: { hdd: "256GB", ram: "" } },
	{ id: 7, name: "갤럭시 S21 울트라", detail: { hdd: "512GB", ram: "" } },
	{ id: 8, name: "갤럭시 S21 울트라", detail: { hdd: "256GB", ram: "" } },
	{ id: 9, name: "갤럭시 S21", detail: { hdd: "256GB", ram: "" } },
	{
		id: 10,
		name: "갤럭시 S20 플러스 BTS 에디션",
		detail: { hdd: "256GB", ram: "" },
	},
	{ id: 11, name: "갤럭시 S20 플러스", detail: { hdd: "256GB", ram: "" } },
	{ id: 12, name: "갤럭시 Z 폴드2", detail: { hdd: "256GB", ram: "" } },
	{ id: 13, name: "갤럭시 Z 폴드3", detail: { hdd: "256GB", ram: "" } },
	{ id: 14, name: "갤럭시 Z 폴드3", detail: { hdd: "512GB", ram: "" } },
];

const product = {
	id: 1,
	name: "갤럭시 S22 플러스",
	detail: { hdd: "256GB", ram: "" },
};

const labels = ["1월", "2월", "3월", "4월", "5월"];

const chartBig = {
	chartX: labels,
	chartY: [770000, 765400, 750000, 775000, 770000, 775300],
};

const chartSmall = {
	chartX: labels,
	chartY: [800000, 745400, 724400, 755000, 750000, 755300],
};

const chartAvg = {
	chartX: labels,
	chartY: [760000, 755400, 740000, 765000, 760000, 765300],
};

const page3Sample = {
	itemId: 6,
	categoryId: 4,
	itemImage:
		"https://shopping-phinf.pstatic.net/main_2941245/29412453621.20211028154519.jpg?type=f640",
	bluetoothVersion: "블루투스5.2",
	shape: "커널형",
	channel: "스테레오",
	purpose: "통화+음악",
	soundQualityFirst: "액티브노이즈캔슬링",
	soundQualitySecond: "공간음향",
	operationFunc: "터치버튼",
	waterproof: "생활방수(IPX4)",
	microphoneYn: "마이크있음",
	usageTime: "3.5시간",
	terminal: "라이트닝",
};

export {
	category1,
	category2,
	mostKeywords,
	product,
	chartBig,
	chartSmall,
	chartAvg,
	labels,
	page3Sample,
};
