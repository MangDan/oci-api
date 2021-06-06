const VueLoaderPlugin = require("vue-loader/lib/plugin");

module.exports = {
  configureWebpack: {
    devServer: {
      //host: 'aig.edunet.net',
      //port: '80'
      host: 'localhost',
      port: '8080'
    }
  },

  transpileDependencies: [
    'vuetify'
  ]
}
