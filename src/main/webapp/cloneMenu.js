$(".addItem").click(function () {
    var $wrapper = $(this).closest('.outerWrapper').children('.containerWrapper');
    $('.cloneWrapper:first-child', $wrapper).clone(true).appendTo($wrapper).find('input').val('').focus();
});

$('.removeItem').click(function () {
    $(this).closest('.cloneWrapper').remove();
});

$('.formWrapper').each(function () {
    var $wrapper = $('.containerPanelWrapper', this);

    $("#addCategory", $(this)).click(function (e) {
        $('.panelCloneWrapper:first-child', $wrapper).clone(true).appendTo($wrapper).removeClass('hidden').find('input').val('').focus();
    });

    $('.panelCloneWrapper #removeCategory', $wrapper).click(function () {
        if ($('.panelCloneWrapper', $wrapper).length > 1)
            $(this).parent().parent().remove();
    });
});
