function getTour() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        //tên API
        url: "http://localhost:8080/api/tour",
        //xử lý khi thành công
        success: function renderTable(data) {
            let itemHtml = "";
            $.each(data, function(index, el) {
                itemHtml += `
                        <tr>
                            <td>${index + 1}</td>
                            <td>${el.code}</td>
                            <td>${el.destination}</td>
                            <td>${el.price}</td>
                            <td>${el.type.name}</td>
                            <td></td>
                            <td></td>
<!--                            <td><a >update</a></td>-->
<!--                            <td><a>delete</a></td>-->
                        </tr>
                `;

            })
            $(".list-tour").html(itemHtml);
        }
    })

}

getTour();
