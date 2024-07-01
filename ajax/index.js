function getTour() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "GET",
        url: "http://localhost:8080/api/tour",
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
                            <td><button class="delete" data-id="${el.id}">delete</button></td>
                        </tr>
                `;
            })
            $(".list-tour").html(itemHtml);
            $(".update").click(function () {
                let id = $(this).data().id;
                showFormUpdate(id)
            });
            $(".delete").click(function () {
                let id = $(this).data().id;
                deletee(id)
            })
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
    let updateTour = {
        code: code,
        destination: destination,
        price: price
    }

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "put",
        data: JSON.stringify(updateTour),
        url: "http://localhost:8080/api/tour/" + id,
        success: function () {
            getTour();
            $("#form").remove();
            console.log("update thành công")
        }
    })
}

function deletee(id) {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "delete",
        url: "http://localhost:8080/api/tour/" + id,
        success: function () {
            getTour();
            console.log("update thành công")
        }
    })
}

function create() {
    let code = $('#code').val();
    let destination = $('#destination').val();
    let price = $('#price').val();
    let type = $('#type').val();
    let newTour = {
        code: code,
        destination: destination,
        price: price,
        type: {
            id: type
        }
    };
    console.log(newTour)
    // gọi phương thức ajax
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "POST",
        data: JSON.stringify(newTour),
        //tên API
        url: "http://localhost:8080/api/tour",
        success: function () {
            getTour();
            alert("Tạo mới thành công")
        }

    });
    //chặn sự kiện mặc định của thẻ
    event.preventDefault();
}

function showFormCreate() {
    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: "get",
        url: "http://localhost:8080/api/type",
        success: function (data) {
            let options = "";
            $.each(data, function (index, el) {
                options += `<option id="type"  value="${el.id}" >${el.name}</option>`;
            })
                let formCreate = `
             <form id="form" novalidate="novalidate">
        <div>
            <label for="code"> code</label>
            <input type="text" id="code" />
        </div>
        <div>
            <label for="destination">destination</label>
            <input type="text" id="destination" />
        </div>
        <div>
            <label for="price">price</label>
            <input type="text" id="price" />
        </div>
        <div>
            <label for="type">type</label>
            <select name="type" id="type">
            ${options}
            </select>
            
        </div>
        <input type="submit" value="Save" id="create-tour"/>
            `
            $(".formCreate").html(formCreate);
            $("#create-tour").click(function (e) {
                e.preventDefault();
                create()
            })

        }
    })

}


getTour();
$("#showFormCreate").click(function () {
    showFormCreate()
});