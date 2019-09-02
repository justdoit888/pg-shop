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
    wx.request({
      url: '',
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
        'alipayNo': self.data.inputalipay == undefined ? '' : self.data.inputalipay
      },
      success:function(res){
        if (res.data.code != '200000'){
          wx.showToast({
            title: res.data.desc,
            icon: 'none'
          })
        }
      }
    })
  }
})