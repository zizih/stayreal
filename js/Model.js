app.Model = function () {
  
  return {
    
    ServerModel: Backbone.Model.extend({
      defaults: function() {
        return {
          ip: app.config.defaultIp,
          port: app.config.defaultPort
        }
      },
      initialize: function () {
      }, 
      getIp: function() {
        return this.get('ip');
      },
      getPort: function() {
        return this.get('port');
      },
      ifNull: function() {
        return this.get('ip') === app.config.defaultIp 
               && this.get('port') === app.config.defaultPort;
      }
    }),
    
    InputModel: Backbone.Model.extend({
      defaults: function() {
        return {
          value: '',
          editing: false
        }
      },
      initialize: function() {
      }, 
      edit: function() {
        return this.get('edting');
      }
    }),
    
    WorksModel: Backbone.Model.extend({
      defaults: function() {
        return {
          works: []
        }
      },
      getSize: function () {
        return this.get('works').length;
      }
    })
    
  }
  
}
