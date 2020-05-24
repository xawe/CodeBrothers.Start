defmodule CodebrothersCustomerWeb.CustomerControllerTest do
  use CodebrothersCustomerWeb.ConnCase

  alias CodebrothersCustomer.Context
  alias CodebrothersCustomer.Context.Customer

  @create_attrs %{
    ativo: true,
    atualizadoem: ~N[2010-04-17 14:00:00],
    cpf: "some cpf",
    criadoem: ~N[2010-04-17 14:00:00],
    datanascimento: ~N[2010-04-17 14:00:00],
    email: "some email",
    id: 42,
    nome: "some nome",
    rg: "some rg",
    sobrenome: "some sobrenome"
  }
  @update_attrs %{
    ativo: false,
    atualizadoem: ~N[2011-05-18 15:01:01],
    cpf: "some updated cpf",
    criadoem: ~N[2011-05-18 15:01:01],
    datanascimento: ~N[2011-05-18 15:01:01],
    email: "some updated email",
    id: 43,
    nome: "some updated nome",
    rg: "some updated rg",
    sobrenome: "some updated sobrenome"
  }
  @invalid_attrs %{ativo: nil, atualizadoem: nil, cpf: nil, criadoem: nil, datanascimento: nil, email: nil, id: nil, nome: nil, rg: nil, sobrenome: nil}

  def fixture(:customer) do
    {:ok, customer} = Context.create_customer(@create_attrs)
    customer
  end

  setup %{conn: conn} do
    {:ok, conn: put_req_header(conn, "accept", "application/json")}
  end

  describe "index" do
    test "lists all tb_customer", %{conn: conn} do
      conn = get(conn, Routes.customer_path(conn, :index))
      assert json_response(conn, 200)["data"] == []
    end
  end

  describe "create customer" do
    test "renders customer when data is valid", %{conn: conn} do
      conn = post(conn, Routes.customer_path(conn, :create), customer: @create_attrs)
      assert %{"id" => id} = json_response(conn, 201)["data"]

      conn = get(conn, Routes.customer_path(conn, :show, id))

      assert %{
               "id" => id,
               "ativo" => true,
               "atualizadoem" => "2010-04-17T14:00:00",
               "cpf" => "some cpf",
               "criadoem" => "2010-04-17T14:00:00",
               "datanascimento" => "2010-04-17T14:00:00",
               "email" => "some email",
               "id" => 42,
               "nome" => "some nome",
               "rg" => "some rg",
               "sobrenome" => "some sobrenome"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn} do
      conn = post(conn, Routes.customer_path(conn, :create), customer: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "update customer" do
    setup [:create_customer]

    test "renders customer when data is valid", %{conn: conn, customer: %Customer{id: id} = customer} do
      conn = put(conn, Routes.customer_path(conn, :update, customer), customer: @update_attrs)
      assert %{"id" => ^id} = json_response(conn, 200)["data"]

      conn = get(conn, Routes.customer_path(conn, :show, id))

      assert %{
               "id" => id,
               "ativo" => false,
               "atualizadoem" => "2011-05-18T15:01:01",
               "cpf" => "some updated cpf",
               "criadoem" => "2011-05-18T15:01:01",
               "datanascimento" => "2011-05-18T15:01:01",
               "email" => "some updated email",
               "id" => 43,
               "nome" => "some updated nome",
               "rg" => "some updated rg",
               "sobrenome" => "some updated sobrenome"
             } = json_response(conn, 200)["data"]
    end

    test "renders errors when data is invalid", %{conn: conn, customer: customer} do
      conn = put(conn, Routes.customer_path(conn, :update, customer), customer: @invalid_attrs)
      assert json_response(conn, 422)["errors"] != %{}
    end
  end

  describe "delete customer" do
    setup [:create_customer]

    test "deletes chosen customer", %{conn: conn, customer: customer} do
      conn = delete(conn, Routes.customer_path(conn, :delete, customer))
      assert response(conn, 204)

      assert_error_sent 404, fn ->
        get(conn, Routes.customer_path(conn, :show, customer))
      end
    end
  end

  defp create_customer(_) do
    customer = fixture(:customer)
    {:ok, customer: customer}
  end
end
