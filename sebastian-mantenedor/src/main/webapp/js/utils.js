function fireEvent(obj,evt) {
    var fireOnThis = obj;
    if( document.createEvent ) {
        var evObj = document.createEvent('MouseEvents');
        evObj.initEvent( evt, true, false );
        fireOnThis.dispatchEvent(evObj);
    } else if( document.createEventObject ) {
        fireOnThis.fireEvent('on'+evt);
    }
}
