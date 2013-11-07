(function (win) {
  /**
   * @auther: hezi
   * @createTime: 2013-11-01
   */
  var App = function () {
    
    this.constr = {};//MVC函数集
    this.constr.routers = {};
    this.constr.models = {};
    this.constr.views = {};
    
    this.routers = {};
    this.models = {};
    this.views = {};
  }
  
  App.prototype.init = function () {
    
    this.constr.routers = new this.Router();
    this.constr.models = new this.Model();
    this.constr.views = new this.View();
    
    // about runtime operate
    this.views.indexView = new this.constr.views.IndexView({
      model: {
        inputModel: new this.constr.models.InputModel(),
        serverModel: new this.constr.models.ServerModel()
      }
    });
    
    this.views.indexView.show();
    
  }
  
  window.app = new App();
  
})(window);
