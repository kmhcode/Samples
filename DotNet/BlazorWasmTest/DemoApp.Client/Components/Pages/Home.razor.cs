using System.Net.Http.Json;
using DemoApp.Client.Shared;
using Microsoft.AspNetCore.Components;

namespace DemoApp.Client.Components.Pages;

partial class Home
{
    [Inject]
    public NavigationManager Navigator { get; set; } = default!;

    public CustomerLogin Login { get; set; } = new();

    public List<CustomerOrder>? Invoice { get; set; }

    public string? ErrorReport { get; set; }

    private async Task FetchCustomerInvoice()
    {
        using var remote = new HttpClient { BaseAddress = new Uri(Navigator.BaseUri) };
        var response = await remote.PostAsJsonAsync("/api/orders", Login);
        if(response.IsSuccessStatusCode)
        {
            Invoice = await response.Content.ReadFromJsonAsync<List<CustomerOrder>>();
            ErrorReport = null;
        }
        else
        {
            Invoice = null;
            ErrorReport = "Authentication Failed";
        }
    }
}