app.Model = function () {
  
  return {
    
    ServerModel: Backbone.Model.extend({
      defaults: function() {
        return {
          ip: app.config.server.defaultIp,
          port: app.config.server.defaultPort
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
        return this.get('ip') === app.config.server.defaultIp 
               && this.get('port') === app.config.server.defaultPort;
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
          work: []
        }
      },
      getSize: function () {
        return this.get('work').length;
      }
    }),
    AblumModel: Backbone.Model.extend({
      defaults: function() {
        return {
          album: []
        }
      },
      getSize: function () {
        return this.get('album').length;
      }
    })
    
  }
  
}
