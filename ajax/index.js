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
        success: function (data) {
            let itemHtml = "";
            $.each(data, function (index, el) {
                itemHtml +=
                    ` 
                        <tr>
                            <td>${index + 1}</td>
                            <td>${el.code}</td>
                            <td>${el.destination}</td>
                            <td>${el.price}</td>
                            <td>${el?.type?.name}</td>
                            <td><button class="update" data-id="${el.id}">update</button></td>
                            <td><a>delete</a></td>
                        </tr>
                `;

            })
            $(".list-tour").html(itemHtml);
            $(".update").click(function () {
                let id = $(this).data().id;
                // lấy id cần update
                showFormUpdate(id)
            });
        }
    })

}


function showFormUpdate(id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "get",
        url: "http://localhost:8080/api/tour/" + id,
        success: function (data) {
            let form = `
             <form id="form" novalidate="novalidate">
        <div>
            <label for="code"> code</label>
            <input type="text" id="code" value="${data.code}"/>
        </div>
        <div>
            <label for="destination">destination</label>
            <input type="text" id="destination" value="${data.destination}"/>
        </div>
        <div>
            <label for="price">price</label>
            <input type="text" id="price" value="${data.price}"/>
        </div>
        
        <input type="submit" value="Edit" id="update-tour" data-id="${data.id}" />
    </form>
            `
            $(".form").html(form);
            $("#update-tour").click(function (e) {
                e.preventDefault();
                update()
            });
        }
    })

}

function update() {
    let code = $('#code').val();
    let destination = $('#destination').val();
    let price = $('#price').val();
    let id = $("#update-tour").data().id
let updateTour ={
    code: code,
    destination:destination,
    price:price
}

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "put",
        data: JSON.stringify(updateTour),
        url: "http://localhost:8080/api/tour/" + id,
        success: function (){
            getTour();
            $("#form").remove();
            console.log("update thành công")
        }
    })
}



getTour();
