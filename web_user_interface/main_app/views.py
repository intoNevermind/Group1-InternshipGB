from django.shortcuts import render, render_to_response

# Create your views here.
def main(request):
    return render_to_response('index.html')

def statistics_global(request):
    return render_to_response('statistics_global.html')

def statistics_by_days(request):
    return render_to_response('statistics_by_days.html')