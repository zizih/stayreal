app.Router = Backbone.Router.extend({

  routes: {
    'index': 'index',
    'i': 'i',
    'server': 'server',
    'posts': 'posts',
    'history': 'history',
    'wish': 'wish'
  },

  initialize: function (){
    this.views = app.views;
  },

  index: function () {
    this.hideAll();
    this.navigate('#index');
    this.views.indexView.show();
  },

  i: function () {
    this.hideAll();
    this.navigate('#i');
    this.views.aboutView = new app.constr.views.IView({
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

  posts: function () {
  	this.hideAll();
  	this.navigate('#posts');
  	this.views.postView = new app.constr.views.PostView({
  		model: {
  		  history: new app.constr.models.PostsModel(app.config.history),
    		heart: new app.constr.models.PostsModel(app.config.heart),
    		summary: new app.constr.models.PostsModel(app.config.summary)
    	}
  	}).show();
  },

  wish: function (){
    this.hideAll();
    this.navigate('#wish');
    this.views.wish = new app.constr.views.WishView({
      model: new app.constr.models.WishModel(app.config.wish)
    }).show();
  },

  hideAll: function () {
    $('section').hide();
  }

});
