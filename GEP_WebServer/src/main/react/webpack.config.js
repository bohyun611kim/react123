const path = require('path');


module.exports = {
	mode: 'development', //'development , production ',
	entry: { 
		menu:'./src/menu.js',
		footer:'./src/footer.js',
		homepage:'./src/pages/main/homepage.js',
		wallet:'./src/pages/wallet/wallet.js',
		exchange:'./src/pages/exchange/exchange.js',
		exchange_buysell:'./src/pages/exchange/exchange_buysell.js',
		product:'./src/pages/product/product.js',
		support:'./src/pages/support/support.js',
		mypage:'./src/pages/mypage/mypage.js',
	},
	output: {
		path: path.resolve(__dirname, 'build'),
		filename: '[name]_bundle.js'
	},
	module: {
		rules: [
			{ // 1
			  test: /\.(js|jsx)$/,
			  include: [
				path.resolve(__dirname, 'src')
			  ],
			  exclude: /node_modules/,
			  use: {
				loader: 'babel-loader',
				options: {
				  presets: ['@babel/preset-env'],
				  plugins: ['@babel/plugin-proposal-class-properties']
				}
			  }
			},
			{
			  	test: /\.(sa|sc|c)ss$/,
			  	use: ["style-loader", "css-loader", "sass-loader"]
			},
			{
				test: /\.(eot|woff|woff2|ttf|svg|png|jpg|gif)$/,
				use: 'url-loader?limit=30000&name=[name]-[hash].[ext]'
			}
		  ],

	},
	externals: {
		'react': 'React',
		'react-dom': "ReactDOM"
	}
}