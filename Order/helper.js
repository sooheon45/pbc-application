window.$HandleBars.registerHelper('TEST', function (fieldDescriptors) {
    for(var i = 0; i < fieldDescriptors.length; i ++ ){
        if(fieldDescriptors[i] && fieldDescriptors[i].className == 'Date'){
        return "import java.util.Date; \n"
        }
    }
});

window.$HandleBars.registerHelper('validate', function (aggregate, options) {
    var keys = Object.keys(aggregate)
    if(keys.length > 1) {
        return options.fn(this);
    }
    return options.inverse(this);
});
