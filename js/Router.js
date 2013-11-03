app.Router = Backbone.Router.extend({
  
  routes: {
    'about' : 'about'
  },
  
  initialize: function (){
    
  },
  
  about: function () {
    this.navigate('#about');
    app.indexView.hide();
  }
  
});
