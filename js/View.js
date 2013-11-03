app.View = function() {

  var IndexView = Backbone.View.extend({

    el: $('#page-index'),

    events: {
      'keyup input': 'unedit'
    },

    initialize: function() {
    },

    template: _.template($('#server-show-template').html()),

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
        if(parseInt($target.val()).toString() !== $target.val() && $target.attr('ctype') === 'number') {
          $target.val($target.val().replace(/[^0-9]/g, ''));
          var alert = new AlertView({
            timeout: 1000,
            firstContent: 'err.',
          });
          alert.show({
            top: (e.target.offsetTop + 200) + 'px',
            left: (e.target.offsetLeft + 100) + 'px'
          });
        } else {
          this.model.serverModel.set($target.attr('name'), $target.val());
        }
      }
      //about server's show
      if(this.model.serverModel.ifNull()) {
        $('#server-show').html($('#server-show').attr('data-default'));
      } else {
        $('#server-show').html(this.template(this.model.serverModel.toJSON()));        
      }
    },
    show: function () {
      this.$el.show();
    },
    hide: function () {
      this.$el.hide();
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
    IndexView : IndexView
  }

}
