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
              imgUrl: './images/test01.jpg',
              imgAlt: '0',
              caption: '0'
            },
            {
              imgUrl: './images/test02.jpg',
              imgAlt: '1',
              caption: '1'
            }
          ]
      })
    }).show();
  },
  
  hideAll: function () {
    $('section').hide();
  }
  
});
