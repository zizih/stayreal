app.Router = Backbone.Router.extend({
  
  routes: {
    'index': 'index',
    'about': 'about',
    'server': 'server'
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
      model: {
        works: [
          new app.constr.models.WorksModel({
            work: app.config.works.gzhacker
          }),
          new app.constr.models.WorksModel({
            work: app.config.works.codejam
          }),
          new app.constr.models.WorksModel({
            work: app.config.works.intern
          }),
          new app.constr.models.WorksModel({
            work: app.config.works.hope
          })
        ],
        albums: new app.constr.models.AblumModel({
          album: app.config.ablum
        })
      }  
    }).show();
  },
  
  server: function () {
    this.hideAll();
    this.navigate('#server');
    this.views.serverView = new app.constr.views.ServerView().show();
  },
  
  hideAll: function () {
    $('section').hide();
  }
  
});
