defmodule CodebrothersCustomerWeb.CustomerController do
  use CodebrothersCustomerWeb, :controller

  alias CodebrothersCustomer.Context
  alias CodebrothersCustomer.Context.Customer

  action_fallback CodebrothersCustomerWeb.FallbackController

  def index(conn, _params) do
    tb_customer = Context.list_tb_customer()
    render(conn, "index.json", tb_customer: tb_customer)
  end

  def create(conn, %{"customer" => customer_params}) do
    with {:ok, %Customer{} = customer} <- Context.create_customer(customer_params) do
      conn
      |> put_status(:created)
      |> put_resp_header("location", Routes.customer_path(conn, :show, customer))
      |> render("show.json", customer: customer)
    end
  end

  def show(conn, %{"id" => id}) do
    customer = Context.get_customer!(id)
    render(conn, "show.json", customer: customer)
  end

  def update(conn, %{"id" => id, "customer" => customer_params}) do
    customer = Context.get_customer!(id)

    with {:ok, %Customer{} = customer} <- Context.update_customer(customer, customer_params) do
      render(conn, "show.json", customer: customer)
    end
  end

  def delete(conn, %{"id" => id}) do
    customer = Context.get_customer!(id)

    with {:ok, %Customer{}} <- Context.delete_customer(customer) do
      send_resp(conn, :no_content, "")
    end
  end
end
