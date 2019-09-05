// pages/modifyInfo/modifyInfo.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    wx.getSetting({
      success: res => {
        if (!res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.showModal({
            title: '提示',
            content: '请先授权登录！',
            showCancel: false,
            success(res) {
              if (res.confirm) {
                wx.switchTab({
                  url: '../me/me',
                })
              }
            }
          })
        }
      }
    })

    var currUser = wx.getStorageSync('currUser');
    var user = app.globalData.userInfo;
    if (user && (currUser == null || currUser == "")) {
      wx.showModal({
        title: '提示',
        content: '您还没有加入我们，请加入我们',
        success(res) {
          if (res.confirm) {
            wx.navigateTo({
              url: '../joinus/joinus',
            })
          } else {
            wx.switchTab({
              url: '../me/me',
            })
          }
        }
      })
    }
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