/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import common_job_properties

// This job runs the suite of ValidatesRunner tests against the Apex runner.
mavenJob('beam_PostCommit_Java_ValidatesRunner_Apex') {
  description('Runs the ValidatesRunner suite on the Apex runner.')
  previousNames('beam_PostCommit_Java_RunnableOnService_Apex')

  // Set common parameters.
  common_job_properties.setTopLevelMainJobProperties(delegate)

  // Set maven parameters.
  common_job_properties.setMavenConfig(delegate)

  // Sets that this is a PostCommit job.
  common_job_properties.setPostCommit(delegate)

  // Allows triggering this build against pull requests.
  common_job_properties.enablePhraseTriggeringFromPullRequest(
    delegate,
    'Apache Apex Runner ValidatesRunner Tests',
    'Run Apex ValidatesRunner')

  // Maven goals for this job.
  goals('''clean verify --projects runners/apex \
      --also-make \
      --batch-mode \
      --errors \
      --activate-profiles validates-runner-tests \
      --activate-profiles local-validates-runner-tests''')
}
