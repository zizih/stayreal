define(function () {
    return Backbone.View.extend({
        template: Handlebars.compile($('#template').html()),

        events: {
            'click .edit': 'edit'
        },

        render: function () {
            //Create and insert the cover letter view
            $(this.el).html(this.template(this.model.toJSON()));
            $('#View').html(this.el);

            return this;
        },

        edit: function () {
            Backbone.history.navigate('Edit/' + this.model.id, true); 
        },
    });
});