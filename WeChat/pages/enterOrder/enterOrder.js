// pages/enterOrder/enterOrder.js
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

  },
  nameinput: function (e) {
    this.setData({
      inputname: e.detail.value
    })
  },
  telinput: function (e) {
    this.setData({
      inputtel: e.detail.value
    })
  },
  qqinput: function (e) {
    this.setData({
      inputqq: e.detail.value
    })
  },
  alipayinput: function (e) {
    this.setData({
      inputalipay: e.detail.value
    })
  },
  remarkinput: function (e) {
    this.setData({
      inputremark: e.detail.value
    })
  },
  orderDateinput: function(e){
    this.setData({
      inputorderDate: e.detail.value
    })
  },
  taphandle: function () {
    var self = this;
    var currUser = wx.getStorageSync('currUser');
    wx.request({
      url: 'http://localhost:8080/wechat/enterOrder',
      data: {
        'tbOrderId': self.data.inputname == undefined ? '' : self.data.inputname,
        'buyerName': self.data.inputtel == undefined ? '' : self.data.inputtel,
        'amount': self.data.inputqq == undefined ? '' : self.data.inputqq,
        'orderDate': self.data.inputorderDate == undefined ? '' : self.data.inputorderDate,
        'tel': self.data.inputalipay == undefined ? '' : self.data.inputalipay,
        "remark": self.data.inputremark == undefined ? '': self.data.inputremark,
        "csUserId": currUser.userId
      },
      success: function (res) {
        if (res.data.code != '200000') {
          wx.showToast({
            title: res.data.desc,
            icon: 'none',
          })
        } else {
          wx.showToast({
            title: "录入成功",
            icon: 'success',
            success: function () {
              app.loadCurrUser();
              setTimeout(function () {
                wx.switchTab({
                  url: '../index/index',
                })
              }, 1500)
            }
          })
        }
      }
    })
  }
})