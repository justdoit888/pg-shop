const app = getApp();
let orderDetail = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderDetail: null
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var currUser = wx.getStorageSync('currUser');
    var that = this;
    wx.request({
      url: 'http://localhost:8080/wechat/queryOrderDetailByOrderId',
      data: {
        orderId: options.orderId,
        role: currUser.role
      },
      success: function (res) {
        console.log(res.data.data);
        that.setData({
          orderDetail: res.data.data
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})