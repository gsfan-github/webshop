/**
 * Created by Focuson on 17-4-11.
 */

function calculateTableHeight(elementNumbers, pageIndex, pageSize) {
    var remainder = elementNumbers % pageSize;
    var lastPageSize = remainder == 0 ? pageSize : remainder;
    var maxPageIndex = remainder == 0 ? elementNumbers / pageSize : parseInt(elementNumbers / pageSize) + 1;
    var currentPageIndex = pageIndex;
    var newHeight = 0;

    if (currentPageIndex < maxPageIndex) {
        newHeight = pageSize * 42 + 130;
    } else {
        newHeight = lastPageSize == 1 ? 192 : lastPageSize * 42 + 130;
    }

    return newHeight;
}

//<!-- in product_manage.js -->
function updateEditColumn(datas) {
    for (var i = 0; i < datas.length; i++) {
        var parentId;
        if (datas[i].parent != null) {
            parentId = datas[i].parent.typeId;
            alert(parentId);
        }
        datas[i].edit = '<a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
        datas[i].createSubCategory = '<a href="#" onclick="addChildProductType(parentId)">创建子类别</a>';
    }

    return datas;
}

function loadTableData(method, url, pageIndex, pageSize) {

    $table.bootstrapTable({
        columns: columns,
        method: method,
        url: url,
        dataType: 'json',
        queryParams: function (params) {
            return 'firstIndex=' + params.offset + '&maxResult=' + params.limit;
        },
        pageIndex: pageIndex,   //初始化页码，默认第一页
        pageSize: pageSize,   //每页的记录行数（*）
        clickToSelect: true,
        responseHandler: function (res) {
            var rows = updateEditColumn(res.pageView.rows);
            return {
                total: res.pageView.total,
                rows: rows
            };
        },
        onLoadSuccess: function (data) {
            //重新设置表格高度
            var elementNumbers = $table.bootstrapTable('getData').length;
            $table.bootstrapTable('resetView', {height: calculateTableHeight(elementNumbers, pageIndex, pageSize)});
        },
        onClickCell: function (row, field, value, $element) {

        },
        onPageChange: function (number, size) {
            pageIndex = number;
            //number 页码，size 页数据条数
            //var elementNumbers = $table.bootstrapTable('getData').length;
            //$table.bootstrapTable('resetView', {height: calculateTableHeight(totalSize, number, size)});
        }
    });

}
