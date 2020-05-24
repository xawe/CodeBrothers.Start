defmodule CodebrothersCustomerWeb.Router do
  use CodebrothersCustomerWeb, :router

  pipeline :api do
    plug :accepts, ["json"]
  end

  scope "/api", CodebrothersCustomerWeb do
    pipe_through :api
  end

  pipeline :browser do
    plug(:accepts, ["html"])
  end

  scope "/", CodebrothersCustomerWeb do
    pipe_through :browser
    # a linha abaixo cria o Crud no endpoint customer.
    # com esses recursos automáticos, não há camada de service para o crud.
    # podemos incluir manualmente depois, se necessário
    resources "/customer", CustomerController, except: [:new, :edit]
    get "/", DefaultController, :index
  end


end
