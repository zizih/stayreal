app.View = function() {
  
  

  var IndexView = Backbone.View.extend({

    el: $('#page-index'),
    events: {
      'keyup input': 'unedit',
      'click #server-show': 'toServer' 
    },
    initialize: function() {
    },
    template: {
      server: _.template($('#server-show-template').html())
    },
    render: function() {
      this.$el.val(this.model.inputModel.get('editing'));
      this.$el.toggleClass('editing', this.model.inputModel.edit());
      return this;
    },
    unedit: function(e) {
      var $target = $(e.currentTarget);
      if (!$target.val()) {
        $target.removeClass('has-input');
        this.model.serverModel.set($target.attr('name'), $target.attr('data-default-' + $target.attr('name')));
      } else {
        $target.addClass('has-input');
        var msg = new RegExp('[^0-9]').test($target.val());
        //do not have correct input && show err alert
        if(msg && $target.attr('ctype') === 'number') {
          $target.val($target.val().replace(/[^0-9]/g, ''));
          var alert = new AlertView();
          alert.show({
            top: (e.target.offsetTop + 200) + 'px',
            left: (e.target.offsetLeft + 100) + 'px',
            timeout: 1000,
            firstContent: '端口不应该是字符啊～',
          });
        } 
        msg = new RegExp('[^0-9\.]').test($target.val());
        if(msg && $target.attr('ctype') === 'ip') {
          $target.val($target.val().replace(/[^0-9\.]/g, ''));
          var alert = new AlertView();
          alert.show({
            top: (e.target.offsetTop + 200) + 'px',
            left: (e.target.offsetLeft - 100) + 'px',
            timeout: 1000,
            firstContent: 'ip没有.和数字以外的字符～',
          });
        }
        this.model.serverModel.set($target.attr('name'), $target.val());
      }
      //about server's show
      if(this.model.serverModel.ifNull()) {
        $('#server-show').html($('#server-show').attr('data-default'));
      } else {
        $('#server-show').html(this.template.server(this.model.serverModel.toJSON()));        
      }
    },
    toServer: function () {
      var server = $('[name="ip"]') + ':' + $('[name="port"]');
      //fail to verify ip
      // var reg = /^(/d{1,3})/.(/d{1,3})/.(/d{1,3})/.(/d{1,3})$/;
      //to server page
      location.href="#server";
    },
    show: function () {
      this.$el.show();
      //modify nav item
      var item0 = $('#header-nav-item0');
      item0.html('i');
      item0.attr('href', '#i');
      return this;
    },
    hide: function () {
      this.$el.hide();
    }
  });
  
  var IView = Backbone.View.extend({
    
    el: $('#page-i'),
    initialize: function () {
      var item0 = $('#header-nav-item0');
      item0.html('home');
      item0.attr('href', '#index');
      //
      this.workModel = this.model.works[2];
      this.workView = new WorksView({
          model: this.workModel 
        });
    },
    events: {
      'click .album a': 'album'
    },
    template: _.template($('#works-album-template').html()),
    show: function () {
      $('.album').html(this.template(this.model.albums.toJSON()));
      this.$el.show();
      return this;
    },
    render: function () {
      return this;
    },
    album: function (e) {
      $target = $(e.target);
      var index =parseInt($target.attr('data-tag-index'));
      this.workModel = this.model.works[index];
      this.workView.resetModel(this.workModel).render();
      //tag
      $('.album').find('a').each(function(target, dom) {
        $(dom).removeClass('select');
      });
      $target.addClass('select');
    }
  });
  
  var WorksView = Backbone.View.extend({
    el: $('#myworks'),
    initialize: function () {
      $('.carousel').carousel({
        interval: 10000
      });
      // this.listenTo(this.model, 'change', this.render); 没有产生作用
      this.render();
    },
    events: {
      'click .carousel-inner img': 'official',  
    },
    template: {
      works: _.template($('#works-item-template').html()),
      indicators: _.template($('#works-indicator-template').html())
    },
    render: function () {
      $('.carousel-inner').html(this.template.works(this.model.toJSON()));
      $('.carousel-indicators').html(this.template.indicators({
        len: this.model.getSize()
      }));
    },
    official: function (e){
      var href = $(e.target).data().href;
      if(!!href) {
        window.open(href);
      }
    },
    resetModel: function (obj) {
      this.model = obj;
      return this;
    } 
  });
  
  var ServerView = Backbone.View.extend({
    el: $('#page-server'),
    initialize: function() {
      var item0 = $('#header-nav-item0');
      item0.html('home');
      item0.attr('href', '#index');
    },
    events: {
      'mouseover .server-item .server-item-title': 'showdesc',
      'mouseout .server-item .server-item-title': 'hidedesc',
      'click .server-item .server-item-title': 'showop',
      'dblclick .server-item .server-item-title': 'hideop',
      'click #insert': 'insert'
    },
    template: {
      //_.template($('#').html())
    },
    insert: function () {
      $.ajax({
        type: 'get',
        url: 'http://10.50.9.27:9600/insert?name=rain&jack=arron',
        success: function (data) {
          console.log(data);
        } 
      });
    },
    showdesc: function (e) {
      $(e.target).next().show();
    },
    hidedesc: function (e) {
      $(e.target).next().hide();
    },
    showop: function (e) {
      this.showdesc(e);
      this.hideAllOp(e);
      $(e.target).next().next().toggle();
      e.preventDefault();
    },
    hideop: function (e) {
      this.hidedesc(e);
      $(e.target).next().next().hide();
      e.preventDefault();
    },
    hideAllOp: function (e) {
      $(e.target).parents().find('.server-item-op').hide();
    },
    render: function(){
      
    },
    show: function() {
      this.$el.show();
      return this;
    }
  });

  var AlertView = Backbone.View.extend({
    el: $('body'),
    initialize : function() {
      this.top = (screen.height - 50) + 'px',
      this.left = (screen.width - 50) + 'px',
      this.timeout = 1000;
      this.firstContent = '有错啦。。。';
    },
    template: _.template($('#alert-template').html()),
    show: function(options) {
      var that = this;
      this.alertView = $(this.template({
        top: options.top || this.top,
        left: options.left || this.left,
        firstContent: options.firstContent || this.firstContent
      }));
      this.$el.append(this.alertView);
      this.alertView.show();
      this.alertViewId = setTimeout(function() {
        that.alertView.hide();
        that.el.removeChild(that.alertView.get(0));
        clearTimeout(that.alertViewId);
      }, options.timeout || this.timeout);
    }
  });

  return {
    IndexView: IndexView,
    IView: IView,
    ServerView: ServerView
  }

}
