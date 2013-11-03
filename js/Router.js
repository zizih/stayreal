app.Router = Backbone.Router.extend({
  
  routes: {
    'about': 'about',
    'index': 'index'
  },
  
  initialize: function (){
    this.views = app.views;
  },
  
  index: function () {
    this.hideAll();
    this.navigate('#index');
    this.views.indexView.show();
  },
  
  about: function () {
    this.hideAll();
    this.navigate('#about');
    this.views.aboutView = new app.constr.views.AboutView({
      model: new app.constr.models.WorksModel({
        works: [
            {
              isFirst: true,
              imgUrl: './images/codejam01.JPG',
              imgAlt: '0',
              caption: '编程进行了24个小时之后，终于到了我们的《唤醒灯》的展示时间，项目点子的作者朱鹏飞在向参赛者解说，我在幕后操作手机客户端，屏幕上看到的黑白时间定时界面，一看就是我的风格了'
            },
            {
              imgUrl: './images/codejam02.JPG',
              imgAlt: '1',
              caption: '演示进行得差不多了，大家都在等手机遥控关灯，结果出意外了出意外了，android和单片机的链接超时已经断开，求开灯原谅'
            },
            {
              imgUrl: './images/codejam03.JPG',
              imgAlt: '2',
              caption: '颁奖的时候，很对不起朱鹏飞，他想了很多天，并且从深圳赶来找硬件工程师大宝（中间）做了充分准备，我写android时没有考虑周全令演示出状况，结果得了第四名'
            },
          ]
      })
    }).show();
  },
  
  hideAll: function () {
    $('section').hide();
  }
  
});
