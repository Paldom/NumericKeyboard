var exec = require('cordova/exec');

module.exports = {
    
    setInputById: function (inputId) {
        // TODO
    },
    
    show: function(success, fail, id) {
        cordova.exec(success, fail, 'NumericKeyboard', 'show', [id]);
    },
    
    hide: function(success, fail) {
        cordova.exec(success, fail, 'NumericKeyboard', 'hide', []);
    }

};