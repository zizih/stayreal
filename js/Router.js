define(['./View', './Edit'], function (View, Edit) {
    return Backbone.Router.extend({
        routes: {
            'View/:id': 'view',
            'Edit/:id': 'edit'
        },

        view: function (id) {
            var model = this.collection.get(id);
            var view = new View({ model: model });
            view.render();
        },

        edit: function (id) {
            var model = this.collection.get(id);
            var edit = new Edit({ model: model });
            edit.render();
        }
    });
});