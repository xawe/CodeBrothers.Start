defmodule CodebrothersCustomerWeb.DefaultController do
  use CodebrothersCustomerWeb, :controller

  def index(conn, _params) do
    text conn, "Hello CodeBrothers"
  end
end
