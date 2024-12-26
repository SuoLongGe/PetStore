$(function (){

    $('#keyword').on('keyup',function (){
        var keyword = $(this).val();
        if(keyword !== ''&&keyword !== null && keyword.length !==0){
            $.ajax({
                type        : 'GET',
                url         : 'http://localhost:8080/PetStore_Web_exploded/productAuto?keyword='+keyword,
                success     : function (data){
                    console.log(data);
                    var productListHTMl = '';
                    for(var i = 0;i < data.length;i++){
                        productListHTMl += '<li class=\"productAutoItem\" data-productId="';
                        productListHTMl += data[i].productId;
                        productListHTMl += '">';
                        productListHTMl += data[i].categoryId;
                        productListHTMl += ': ';
                        productListHTMl += data[i].name;
                        productListHTMl += '</li>';
                    }
                    $('#productAutoList').html(productListHTMl);
                    $('#productAutoComplete').show();
                },
                error       :function (errorMsg){
                    console.log(errorMsg);
                }
            });
        }else {
            $('#productAutoComplete').hide();
        }
    });

    $(document).on('click','.productAutoItem',function (){
        var productId = $(this).data('productid');
        // console.log(productId +"有没有");
        $('#productAutoComplete').hide();
        $('#keyword').val('');
        //
        console.log(productId);
        window.location.href = 'http://localhost:8080/PetStore_Web_exploded/productForm?productId='+productId;
    });

    $('#productAutoComplete').on('mouseleave',function (){
        $(this).hide();
        $('#keyword').val('');
    })
})