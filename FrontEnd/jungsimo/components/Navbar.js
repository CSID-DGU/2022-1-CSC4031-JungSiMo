import logo from "../assets/icons/logo.svg";
import Image from "next/image";

const Navbar = () => {
	return (
		<div className="h-[52px] w-full flex px-[15px] pt-[1vh] justify-between">
			<Image src={logo} alt="로고" className="h-[52px] w-auto" />
		</div>
	);
};

export default Navbar;
