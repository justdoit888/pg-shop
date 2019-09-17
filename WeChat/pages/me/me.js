// pages/me/me.js
const app = getApp();
Page({

  // 页面的初始数据
  data: {
    isShowUserName: false,
    userInfo: null,
    role : 3,
  },

  // button获取用户信息
  onGotUserInfo: function (e) {
    if (e.detail.userInfo) {
      var user = e.detail.userInfo;
      this.setData({
        isShowUserName: true,
        userInfo: e.detail.userInfo,
      })
      user.openid = app.globalData.openid;
      app._saveUserInfo(user);
    } else {
      wx.showToast({
        title: '登陆需要允许授权',
        icon: 'none',
        success: function () {
          setTimeout(function () {
            wx.switchTab({
              url: '../me/me',
            })
          }, 1000)
        }
      })
    }
  },

  goToMyOrder: function () {
    wx.navigateTo({
      url: '../myOrder/myOrder',
    })
  },

  goToModifyInfo: function () {
    wx.navigateTo({
      url: '../modifyInfo/modifyInfo',
    })
  },
  goToCancelAccount: function () {
    wx.navigateTo({
      url: '../cancelAccount/cancelAccount',
    })
  },
  goToEnterOrder: function () {
    wx.navigateTo({
      url: '../enterOrder/enterOrder',
    })
  },
  goToSettleInfo: function () {
    wx.navigateTo({
      url: '../settleInfo/settleInfo',
    })
  },
  //生命周期函数--监听页面加载
  onLoad: function (options) {
    var that = this;
    var user = app.globalData.userInfo;
    if (user) {
      that.setData({
       isShowUserName: true,
       userInfo: user,
      })
    } else {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        that.setData({
          userInfo: res.userInfo,
          isShowUserName: true
        })
      }
    }
  },
  onShow : function(){
    var that = this;
    app.loadCurrUser();
    var currUser = wx.getStorageSync('currUser');
    var role = currUser == null || currUser == "" ? -1 : currUser.role;
    if (role != null) {
      that.setData({
        role: role
      })
    }
  }
})