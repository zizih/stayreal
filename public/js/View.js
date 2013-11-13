app.View = function() {

  var IndexView = Backbone.View.extend({

    el : $('#page-index'),
    events : {
      'keypress #comment' : 'edit',
      'keyup input' : 'unedit',
      'click #to-demo' : 'todemo'
    },
    initialize : function() {
      try {
        if (!app.iAjax) {
          app.iAjax = new iAjax();
        }
        var that = this;
        app.iAjax.get({
          url : 'fetchComments',
          success : function(data) {
            for (i in data) {
              that.render(new app.constr.models.CommentModel({
                content : data[i].content,
                id : data[i].id
              }).setTime(data[i].time));
            }
          },
          err : function(err) {
            console.log(err);
          }
        });
      } catch (ex) {
        var alert = new AlertView();
        alert.show({
          top : 500 + 'px',
          left : 500 + 'px',
          timeout : 3000,
          firstContent : '不要ajax请求报错，先运行iserver吖  ~ ^_^ .... ',
        });
      }
    },
    template : _.template($('#comment-template').html()),
    render : function() {
      return this;
    },
    render : function(model) {
      $('#comments').append(this.template(model.toJSON()));
    },
    add : function(str) {
      this.model.commentModel.updateTime().set('content', str);
      $('#comments').append(this.template(this.model.commentModel.toJSON()));
      this.save();
    },
    save : function() {
      var model = this.model.commentModel, url = 'insertComment?content=' + model.get('content');
      console.log(url);
      app.iAjax.get({
        url : url,
        //iserver post 请求还没有实现获取请求体
        // data: this.model.commentModel.toJSON(),
        success : function(data) {
          console.log(data);
        },
        err : function(err) {
          console.log();
        }
      });
    },
    edit : function(e) {
      if (e.keyCode == 13) {
        var $target = $(e.target);
        this.add($target.val());
        $target.val('');
      }
    },
    todemo : function(e) {
      var alert = new AlertView();
      alert.show({
        top : (e.target.offsetTop + 200) + 'px',
        left : (e.target.offsetLeft + 100) + 'px',
        timeout : 1000,
        firstContent : 'Unfinished Page ～ O_O ~',
      });
      location.href = "#server";
    },
    show : function() {
      this.$el.show();
      //modify nav item
      var item0 = $('#header-nav-item0');
      item0.html('i');
      item0.attr('href', '#i');
      return this;
    },
    hide : function() {
      this.$el.hide();
    }
  });

  var IView = Backbone.View.extend({

    el : $('#page-i'),
    initialize : function() {
      var item0 = $('#header-nav-item0');
      item0.html('home');
      item0.attr('href', '#index');
      //
      this.defaultAblum = 2;
      this.jsWorks = this.model.works;
      this.jsAblums = this.model.albums;
      this.workModel = this.model.works[this.defaultAblum];
      this.workView = new WorksView({
        model : this.workModel
      });
    },
    events : {
      'click .album a' : 'album',
      'click #to-iserver-album' : 'toiserver'
    },
    template : _.template($('#works-album-template').html()),
    show : function() {
      $('.album').html(this.template(this.model.albums.toJSON()));
      this.$el.show();
      return this;
    },
    render : function() {
      this.show();
      return this;
    },
    album : function(e) {
      $target = $(e.target);
      var index = parseInt($target.attr('data-tag-index'));
      this.workModel = this.model.works[index-1];
      this.workView.resetModel(this.workModel).render();
      //tag
      $('.album').find('a').each(function(target, dom) {
        $(dom).removeClass('select');
      });
      $target.addClass('select');
    },
    toiserver : function(e) {
      var $target = $(e.target);
      console.log($target.attr('data-src'));
      if($target.attr('data-src') === 'server') {
        this.model.works = this.jsWorks;
        this.model.albums = this.jsAblums;
        this.workView.resetModel(this.model.works[this.defaultAblum]).render();
        this.render(); //关于album名字的渲染
        $target.attr('data-src','jsconf').html('切换到后台');
      } else if($target.attr('data-src') === 'jsconf'){
        try {
          if (!app.iAjax) {
            app.iAjax = new iAjax();
          }
          var that = this;
          app.iAjax.get({
            url : 'fetchAlbums',
            success : function(data) {
              that.model.albums = new app.constr.models.AblumModel({
                album : data.albums
              });
              var carouselArr = [];
              for(i in data.carousels) {
                carouselArr[i] = new app.constr.models.WorksModel({
                  work: data.carousels[i]
                }); 
              }
              that.model.works = carouselArr;
              that.workView.resetModel(that.model.works[0]).render();
              that.render();
              $target.attr('data-src','server').html('切换到前端');
            },
            err : function(err) {
              console.log(err);
            }
          });
        } catch (ex) {
          //deal with ex  
        }
      }
    }
  });

  var WorksView = Backbone.View.extend({
    el : $('#myworks'),
    initialize : function() {
      $('.carousel').carousel({
        interval : 10000
      });
      // this.listenTo(this.model, 'change', this.render); 没有产生作用
      this.render();
    },
    events : {
      'click .carousel-inner img' : 'official',
    },
    template : {
      works : _.template($('#works-item-template').html()),
      indicators : _.template($('#works-indicator-template').html())
    },
    render : function() {
      $('.carousel-inner').html(this.template.works(this.model.toJSON()));
      $('.carousel-indicators').html(this.template.indicators({
        len : this.model.getSize()
      }));
    },
    official : function(e) {
      var href = $(e.target).data().href;
      if (!!href) {
        window.open(href);
      }
    },
    resetModel : function(obj) {
      this.model = obj;
      return this;
    }
  });

  var ServerView = Backbone.View.extend({
    el : $('#page-server'),
    initialize : function() {
      var item0 = $('#header-nav-item0');
      item0.html('home');
      item0.attr('href', '#index');
    },
    events : {
      'mouseover .server-item .server-item-title' : 'showdesc',
      'mouseout .server-item .server-item-title' : 'hidedesc',
      'click .server-item .server-item-title' : 'showop',
      'dblclick .server-item .server-item-title' : 'hideop',
      'click #insert' : 'insert'
    },
    template : {
      //_.template($('#').html())
    },
    insert : function() {
      $.ajax({
        type : 'get',
        url : 'insert?name=rain&jack=arron',
        success : function(data) {
          console.log(data);
        }
      });
    },
    showdesc : function(e) {
      $(e.target).next().show();
    },
    hidedesc : function(e) {
      $(e.target).next().hide();
    },
    showop : function(e) {
      this.showdesc(e);
      this.hideAllOp(e);
      $(e.target).next().next().toggle();
      e.preventDefault();
    },
    hideop : function(e) {
      this.hidedesc(e);
      $(e.target).next().next().hide();
      e.preventDefault();
    },
    hideAllOp : function(e) {
      $(e.target).parents().find('.server-item-op').hide();
    },
    render : function() {

    },
    show : function() {
      this.$el.show();
      return this;
    }
  });

  var AlertView = Backbone.View.extend({
    el : $('body'),
    initialize : function() {
      this.top = (screen.height - 50) + 'px', this.left = (screen.width - 50) + 'px', this.timeout = 1000;
      this.firstContent = '有错啦。。。';
    },
    template : _.template($('#alert-template').html()),
    show : function(options) {
      var that = this;
      this.alertView = $(this.template({
        top : options.top || this.top,
        left : options.left || this.left,
        firstContent : options.firstContent || this.firstContent
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
    IndexView : IndexView,
    IView : IView,
    ServerView : ServerView
  }

}
