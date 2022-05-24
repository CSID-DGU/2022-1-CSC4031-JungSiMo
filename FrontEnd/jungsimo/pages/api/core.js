// // import NextCors from "nextjs-cors";

// // async function handler(req, res) {
// // 	// Run the cors middleware
// // 	// nextjs-cors uses the cors package, so we invite you to check the documentation https://github.com/expressjs/cors
// // 	await NextCors("http://localhost:8080/api/v1/search/category", res, {
// // 		// Options
// // 		methods: ["GET", "HEAD", "PUT", "PATCH", "POST", "DELETE"],
// // 		origin: "*",
// // 		optionsSuccessStatus: 200, // some legacy browsers (IE11, various SmartTVs) choke on 204
// // 	});

// // 	// Rest of the API logic
// // 	// res.json({ message: "Hello NextJs Cors!" });
// // 	return { props: { res } };
// // }

// import Cors from "cors";

// // Initializing the cors middleware
// const cors = Cors({
// 	methods: ["GET", "HEAD"],
// });

// // Helper method to wait for a middleware to execute before continuing
// // And to throw an error when an error happens in a middleware
// function runMiddleware(req, res, fn) {
// 	return new Promise((resolve, reject) => {
// 		fn(req, res, (result) => {
// 			if (result instanceof Error) {
// 				return reject(result);
// 			}

// 			return resolve(result);
// 		});
// 	});
// }

// export async function handler(req, res) {
// 	// Run the middleware
// 	await runMiddleware(
// 		"http://localhost:8080/api/v1/search/category",
// 		res,
// 		cors
// 	);

// 	// Rest of the API logic
// 	return { props: { res } };
// }
