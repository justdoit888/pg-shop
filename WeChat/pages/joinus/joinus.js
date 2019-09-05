const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    selectData: ['程序员','客服'],
    index: 0
  },
  selectTap() {
    this.setData({
      show: !this.data.show
    });
  },
  // 点击下拉列表
  optionTap(e) {
    let Index = e.currentTarget.dataset.index;//获取点击的下拉列表的下标
    this.setData({
      index: Index,
      show: !this.data.show
    });
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
  taphandle:function(){
    var self = this;
    wx.request({
      url: 'http://localhost:8080/wechat/joinus',
      data:{
        'openId':wx.getStorageSync("openId"),
        'userName': self.data.inputname == undefined ? '' : self.data.inputname,
        'tel': self.data.inputtel == undefined ? '' : self.data.inputtel,
        'qqNumber': self.data.inputqq == undefined ? '' : self.data.inputqq,
        'alipayNo': self.data.inputalipay == undefined ? '' : self.data.inputalipay,
        "role": self.data.index == 0 ? 3 : 2
      },
      success:function(res){
        if (res.data.code != '200000'){
          wx.showToast({
            title: res.data.desc,
            icon: 'none',
          })
        }else{
          wx.showToast({
            title: "加入成功",
            icon: 'success',
            success: function(){
              app.loadCurrUser();
              setTimeout(function(){
                wx.switchTab({
                  url: '../index/index',
                })
              },1500)
            }
          })
        }
      }
    })
  }
})