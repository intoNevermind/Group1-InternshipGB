from django.shortcuts import render, render_to_response


# Create your views here.
def main(request):
    return render_to_response('index.html')


def statistics_global(request):
    return render_to_response('statistics_global.html')


def statistics_by_days(request):
    return render_to_response('statistics_by_days.html')


def preferences_persons(request):
    return render_to_response('preferences_persons.html')


def preferences_keywords(request):
    return render_to_response('preferences_keywords.html')


def preferences_sites(request):
    return render_to_response('preferences_sites.html')


def config_users(request):
    return render_to_response('config_users.html')
