/**
 * Created by Focuson on 17-4-11.
 */



function loadTableData(method, url, pageIndex, pageSize) {

    columns = [{
            field: 'typeId',
            title: '代号'
        },
        {
            field: 'name',
            title: '产品类别名称'
        },
        {
            field: 'parent',
            title: '所属父类'
        },
        {
            field: 'note',
            title: '备注'
        },
        {
            field: 'createSubCategory',
            title: '创建下级类别'
        },
        {
            field: 'edit',
            title: '修改'
        }];

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


function updateEditColumn(datas) {
    for (var i = 0; i < datas.length; i++) {
        datas[i].edit = '<a href="#"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span></a>';
    }

    return datas;
}