/**
 * Created by Focuson on 17-4-11.
 */

function initialize() {
    config = {
        'method': 'GET',
        'url': '/getScrollData'
    };
}

function initializeTableTitle(idName) {
    if (idName == undefined) {
        columns = [];
        return;
    }

    if (idName == 'productTypeManage') {
        columns = [{
            field: 'typeId',
            title: '代号',
            align: 'center'
        },
            {
                field: 'name',
                title: '产品类别名称',
                align: 'center'
            },
            {
                field: 'parent',
                title: '所属父类',
                align: 'center'
            },
            {
                field: 'note',
                title: '备注',
                align: 'center'
            },
            {
                field: 'createSubCategory',
                title: '创建下级类别',
                align: 'center'
            },
            {
                field: 'edit',
                title: '修改',
                align: 'center',
                visible: false
            }];
    }
}



function addProductType() {
    var name = $('#productName').val();
    var note = $('#productNote').val();
    var parentId = $('#productParentId').val();
    $.ajax({
        'method': 'POST',
        'url': '/create',
        'async': false,
        'data': "{\"name\": \"" + name + "\", \"note\":\"" + note + "\", \"parentId\":\"" + parentId + "\"}",
        'headers': {
            "content-type": "application/json",
            "cache-control": "no-cache"
        },
        'success': function (data, status) {
            if (data.status == 'SUCCESS' && status == 'success') {
                alert("添加成功");
            } else {
                alert("添加失败");
            }
            $('#addProductTypeModal').modal('hide');
            $('#addProductTypeModal').find('input').each(function () {   //清空input val
                $(this).val("");
            });
        }
    });
}