import Navbar from "../components/Navbar";

export default function Layout({ children }) {
	return (
		<div className="bg-[#F4F4F4] w-full h-full">
			<div className="mx-auto max-w-[500px] min-w-[360px] bg-white h-screen w-full max-h-screen">
				<Navbar />
				<main>{children}</main>
			</div>
		</div>
	);
}
