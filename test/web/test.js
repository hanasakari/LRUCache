/**
 * Created by AmamiyaAsuka on 2017/7/27.
 */
$("#test").click(function () {
    window.setInterval(save, 100);
    var id = 1;
    var value = 1;
    function save() {
        $.ajax({
            url: "/cache/save",
            data: { "id": id, "value": value },
            dataType : "Json",
            success: function ( data ) {
            }
        });

        $.ajax({
            url: "/cache/load",
            data: { "id": id-100 },
            dataType : "Json",
            success: function ( data ) {

            }
        });
        id++;
        value++;
    }
})