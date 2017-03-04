package com.singhajit.rubygems.gemdetails;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import com.google.gson.Gson;
import com.singhajit.rubygems.trending.model.Gem;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.singhajit.rubygems.gemdetails.GemActivity.GEM_EXTRA;

public class GemActivityTest {
  @Rule
  public ActivityTestRule<GemActivity> rule = new ActivityTestRule<>(GemActivity.class, true, false);

  @Test
  public void shouldRenderGemDetails() throws Exception {
    Gem gem = new Gson().fromJson(getGemResponse(), Gem.class);
    Intent intent = new Intent(InstrumentationRegistry.getTargetContext(), GemActivity.class);
    intent.putExtra(GEM_EXTRA, gem);
    rule.launchActivity(intent);
    TimeUnit.MINUTES.sleep(2);
  }

  private String getGemResponse() {
    return "{\n" +
        "  \"name\": \"gocd-slack-server\",\n" +
        "  \"downloads\": 9759,\n" +
        "  \"version\": \"0.0.26\",\n" +
        "  \"version_downloads\": 960,\n" +
        "  \"platform\": \"ruby\",\n" +
        "  \"authors\": \"Seo Townsend\",\n" +
        "  \"info\": \"It is not a gocd plugin, it uses Gocd's API to relay information\",\n" +
        "  \"licenses\": [\n" +
        "    \"MIT\"\n" +
        "  ],\n" +
        "  \"metadata\": {},\n" +
        "  \"sha\": \"5009c6335f9455934cb2364ad0a80f897e74a2c6f07ad6a83aa8c09bfca41d74\",\n" +
        "  \"project_uri\": \"https://rubygems.org/gems/gocd-slack-server\",\n" +
        "  \"gem_uri\": \"https://rubygems.org/gems/gocd-slack-server-0.0.26.gem\",\n" +
        "  \"homepage_uri\": \"https://github.com/sotownsend/gocd-slack-server\",\n" +
        "  \"wiki_uri\": \"http://wiki.github.com/gocd/gocd\",\n" +
        "  \"documentation_uri\": \"http://gocd.info\",\n" +
        "  \"mailing_list_uri\": \"http://groups.google.com/group/gocd\",\n" +
        "  \"source_code_uri\": \"http://github.com/gocd/gocd-ruby\",\n" +
        "  \"bug_tracker_uri\": \"https://github.com/gocd/gocd-ruby/issues\",\n" +
        "  \"dependencies\": {\n" +
        "    \"development\": [\n" +
        "      {\n" +
        "        \"name\": \"bundler\",\n" +
        "        \"requirements\": \"~\\u003e 1.6\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"rake\",\n" +
        "        \"requirements\": \"~\\u003e 10.3\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"rspec\",\n" +
        "        \"requirements\": \"~\\u003e 3.2\"\n" +
        "      }\n" +
        "    ],\n" +
        "    \"runtime\": [\n" +
        "      {\n" +
        "        \"name\": \"activesupport\",\n" +
        "        \"requirements\": \"~\\u003e 4.2\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"slack-notifier\",\n" +
        "        \"requirements\": \"~\\u003e 1.1\"\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "}";
  }
}