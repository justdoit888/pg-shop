//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        var self = this;
        var openId = wx.getStorageSync('openId');
        if (openId == '' || openId == null){
          wx.request({
            url: 'http://localhost:8080/wechat/getOpenId',
            data:{
              "code":res.code
            },
            header: {
              'Content-Type': 'application/x-www-form-urlencoded'
            },
            method: 'POST',
            success:function(result){
              if(result.data.code == '200000'){
                wx.setStorageSync('openId', result.data.data);
              }else{
                wx.setStorageSync('openId', result.data.data.openid);
                wx.setStorageSync('currUser',result.data.data);
              }            
            }
          })
        }else{
          this.userInfoReadyCallback = res => {
            self._saveUserInfo(res.userInfo);
          }
        }
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
  },
  _saveUserInfo: function (user) {
    this.globalData.userInfo = user;
  },
  loadCurrUser: function(){
    wx.request({
      url: 'http://localhost:8080/wechat/queryCurrUser',
      data: {
        "openId": wx.getStorageSync('openId')
      },
      header: {
        'Content-Type': 'application/x-www-form-urlencoded'
      },
      method: 'POST',
      success: function (result) {
        wx.setStorageSync('currUser', result.data.data);
      }
    })
  }
})